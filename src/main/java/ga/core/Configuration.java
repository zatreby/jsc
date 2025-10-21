package ga.core;

import ga.chromosome.*;
import ga.operators.crossover.*;
import ga.operators.crossover.impl.SinglePointCrossover;
import ga.operators.mutation.*;
import ga.operators.mutation.impl.BitFlipMutation;
import ga.operators.replacement.impl.GenerationalReplacement;
import ga.operators.selection.*;
import ga.operators.replacement.*;

import ga.operators.selection.impl.RouletteWheelSelection;
import ga.problem.*;

public class Configuration {
    private int populationSize = 100;
    private int chromosomeLength = 10;
    private ChromosomeType chromosomeType = ChromosomeType.BINARY;
    private int generations = 100;
    private int numberOfParents = 50;
    private double crossoverRate = 0.7;
    private double mutationRate = 0.01;
    private double lowerBound = 0.0;
    private double upperBound = 1.0;
    private boolean verbose = false;

    private FitnessFunction fitnessFunction;
    private ConstraintHandler constraintHandler;

    private SelectionOperator selectionOperator = new RouletteWheelSelection();
    private CrossoverOperator crossoverOperator = new SinglePointCrossover();
    private MutationOperator mutationOperator = new BitFlipMutation();
    private ReplacementStrategy replacementStrategy = new GenerationalReplacement();

    public int populationSize() {
        return populationSize;
    }

    public int chromosomeLength() {
        return chromosomeLength;
    }

    public ChromosomeType chromosomeType() {
        return chromosomeType;
    }

    public int generations() {
        return generations;
    }

    public int numberOfParents() {
        return numberOfParents;
    }

    public double crossoverRate() {
        return crossoverRate;
    }

    public double mutationRate() {
        return mutationRate;
    }

    public double lowerBound() {
        return lowerBound;
    }

    public double upperBound() {
        return upperBound;
    }

    public boolean verbose() {
        return verbose;
    }

    public FitnessFunction fitnessFunction() {
        return fitnessFunction;
    }

    public ConstraintHandler constraintHandler() {
        return constraintHandler;
    }

    public SelectionOperator selectionOperator() {
        return selectionOperator;
    }

    public CrossoverOperator crossoverOperator() {
        return crossoverOperator;
    }

    public MutationOperator mutationOperator() {
        return mutationOperator;
    }

    public ReplacementStrategy replacementStrategy() {
        return replacementStrategy;
    }

    public void setPopulationSize(int size) {
        this.populationSize = size;
    }

    public void setChromosomeLength(int length) {
        this.chromosomeLength = length;
    }

    public void setChromosomeType(ChromosomeType type) {
        this.chromosomeType = type;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public void setNumberOfParents(int numberOfParents) {
        this.numberOfParents = numberOfParents;
    }

    public void setCrossoverRate(double rate) {
        this.crossoverRate = rate;
    }

    public void setMutationRate(double rate) {
        this.mutationRate = rate;
    }

    public void setLowerBound(double bound) {
        this.lowerBound = bound;
    }

    public void setUpperBound(double bound) {
        this.upperBound = bound;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setFitnessFunction(FitnessFunction function) {
        this.fitnessFunction = function;
    }

    public void setConstraintHandler(ConstraintHandler handler) {
        this.constraintHandler = handler;
    }

    public void setSelectionOperator(SelectionOperator operator) {
        this.selectionOperator = operator;
    }

    public void setCrossoverOperator(CrossoverOperator operator) {
        this.crossoverOperator = operator;
    }

    public void setMutationOperator(MutationOperator operator) {
        this.mutationOperator = operator;
    }

    public void setReplacementStrategy(ReplacementStrategy strategy) {
        this.replacementStrategy = strategy;
    }
}
