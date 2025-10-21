package ga.core;

import ga.chromosome.*;
import ga.operators.crossover.*;
import ga.operators.mutation.*;
import ga.operators.selection.*;
import ga.operators.replacement.*;
import ga.problem.*;

import java.util.*;
import java.util.stream.*;

public class Engine {
    private final Configuration config;
    private Population population;
    private Chromosome bestSolution;
    private final List<Double> fitnessHistory;

    public Engine() {
        this.config = new Configuration();
        this.fitnessHistory = new ArrayList<>();
    }

    public void run() {
        validateConfiguration();
        initializePopulation();
        evaluatePopulation();

        for (int generation = 0; generation < config.generations(); generation++) {
            List<Chromosome> parents = selectParents();
            List<Chromosome> offspring = reproduce(parents);
            evaluateOffspring(offspring);
            population = replacePopulation(offspring);

            updateBestSolution();
            fitnessHistory.add(bestSolution.getFitness());

            if (config.verbose()) {
                System.out.printf("Generation %d: Best Fitness = %.4f%n",
                        generation + 1, bestSolution.getFitness());
            }
        }
    }

    private void validateConfiguration() {
        if (config.fitnessFunction() == null) {
            throw new IllegalStateException("Fitness function must be set");
        }
        if (config.chromosomeType() == null) {
            throw new IllegalStateException("Chromosome type must be set");
        }
    }

    private void initializePopulation() {
        List<Chromosome> chromosomes = new ArrayList<>();
        for (int i = 0; i < config.populationSize(); i++) {
            Chromosome chromosome = createChromosome();
            if (chromosome != null) {
                chromosome.initialize(config.chromosomeLength(), config.lowerBound(), config.upperBound());
            }
            chromosomes.add(chromosome);
        }
        population = new Population(chromosomes);
    }

    private Chromosome createChromosome() {
        return config.chromosomeType().get();
    }

    private void evaluatePopulation() {
        population.getChromosomes().parallelStream().forEach(this::evaluateChromosome);
    }

    private void evaluateChromosome(Chromosome chromosome) {
        if (config.constraintHandler() != null &&
                config.constraintHandler().isInfeasible(chromosome)) {
            chromosome.setFitness(config.constraintHandler().getPenalizedFitness(chromosome, config.fitnessFunction(), null));
        } else {
            chromosome.setFitness(config.fitnessFunction().evaluate(chromosome));
        }
    }

    private List<Chromosome> selectParents() {
        return config.selectionOperator()
                .select(population, config.numberOfParents());
    }

    private List<Chromosome> reproduce(List<Chromosome> parents) {
        List<Chromosome> offspring = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < parents.size() - 1; i += 2) {
            Chromosome parent1 = parents.get(i);
            Chromosome parent2 = parents.get(i + 1);

            if (random.nextDouble() < config.crossoverRate()) {
                List<Chromosome> children = config.crossoverOperator()
                        .crossover(parent1, parent2, config.chromosomeType());
                offspring.addAll(children);
            } else {
                offspring.add(parent1.clone());
                offspring.add(parent2.clone());
            }
        }

        offspring.forEach(child -> {
            if (random.nextDouble() < config.mutationRate()) {
                config.mutationOperator().mutate(child);
            }
        });

        return offspring;
    }

    private void evaluateOffspring(List<Chromosome> offspring) {
        offspring.parallelStream().forEach(this::evaluateChromosome);
    }

    private Population replacePopulation(List<Chromosome> offspring) {
        return config.replacementStrategy()
                .replace(population, offspring, config.populationSize());
    }

    private void updateBestSolution() {
        Chromosome currentBest = population.getBest();
        if (bestSolution == null || currentBest.getFitness() > bestSolution.getFitness()) {
            bestSolution = currentBest.clone();
        }
    }

    public Chromosome getBestSolution() {
        return bestSolution;
    }

    public List<Double> getFitnessHistory() {
        return new ArrayList<>(fitnessHistory);
    }

    public Population getPopulation() {
        return population;
    }

    // Configuration setters
    public void setPopulationSize(int size) {
        config.setPopulationSize(size);
    }

    public void setChromosomeLength(int length) {
        config.setChromosomeLength(length);
    }

    public void setChromosomeType(ChromosomeType type) {
        config.setChromosomeType(type);
    }

    public void setGenerations(int generations) {
        config.setGenerations(generations);
    }

    public void setNumberOfParents(int numberOfParents) {
        config.setNumberOfParents(numberOfParents);
    }

    public void setCrossoverRate(double rate) {
        config.setCrossoverRate(rate);
    }

    public void setMutationRate(double rate) {
        config.setMutationRate(rate);
    }

    public void setFitnessFunction(FitnessFunction fitnessFunction) {
        config.setFitnessFunction(fitnessFunction);
    }

    public void setConstraintHandler(ConstraintHandler handler) {
        config.setConstraintHandler(handler);
    }

    public void setSelectionOperator(SelectionOperator operator) {
        config.setSelectionOperator(operator);
    }

    public void setCrossoverOperator(CrossoverOperator operator) {
        config.setCrossoverOperator(operator);
    }

    public void setMutationOperator(MutationOperator operator) {
        config.setMutationOperator(operator);
    }

    public void setReplacementStrategy(ReplacementStrategy strategy) {
        config.setReplacementStrategy(strategy);
    }

    public void setBounds(double lower, double upper) {
        config.setLowerBound(lower);
        config.setUpperBound(upper);
    }

    public void setVerbose(boolean verbose) {
        config.setVerbose(verbose);
    }
}
