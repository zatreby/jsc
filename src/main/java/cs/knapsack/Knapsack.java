package cs.knapsack;

import ga.chromosome.BinaryChromosome;
import ga.chromosome.ChromosomeType;
import ga.core.Engine;
import ga.operators.crossover.impl.SinglePointCrossover;
import ga.operators.mutation.impl.BitFlipMutation;
import ga.operators.replacement.impl.GenerationalReplacement;
import ga.operators.selection.impl.RouletteWheelSelection;

public class Knapsack {

    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    static final int MAX_WEIGHT = 100;

    public static void main(String[] args) {
        // Setup problem
        Item[] items = {
                new Item(10, 60),
                new Item(20, 100),
                new Item(30, 120),
                new Item(15, 80),
                new Item(25, 90),
                new Item(12, 70),
                new Item(18, 85),
                new Item(22, 95),
                new Item(8, 40),
                new Item(14, 75)
        };

        // Setup engine
        Engine engine = new Engine();

        engine.setFitnessFunction(chromosome -> {
                    BinaryChromosome binary = (BinaryChromosome) chromosome;
                    int totalValue = 0;
                    int totalWeight = 0;

                    for (int i = 0; i < binary.getLength(); i++) {
                        if (binary.getGene(i)) {
                            totalValue += items[i].value;
                            totalWeight += items[i].weight;
                        }
                    }

                    return totalWeight <= MAX_WEIGHT ? totalValue : 0;
                }
        );

        engine.setConstraintHandler(chromosome -> {
                    BinaryChromosome binary = (BinaryChromosome) chromosome;
                    int totalWeight = 0;

                    for (int i = 0; i < binary.getLength(); i++) {
                        if (binary.getGene(i)) {
                            totalWeight += items[i].weight;
                        }
                    }

                    return totalWeight > MAX_WEIGHT;
                }
        );

        // Configure Engine
        engine.setChromosomeType(ChromosomeType.BINARY);
        engine.setChromosomeLength(items.length);
        engine.setPopulationSize(100);
        engine.setGenerations(1000);
        engine.setNumberOfParents(10);
        engine.setCrossoverRate(0.5);
        engine.setMutationRate(0.5);
        engine.setSelectionOperator(new RouletteWheelSelection());
        engine.setCrossoverOperator(new SinglePointCrossover());
        engine.setMutationOperator(new BitFlipMutation());
        engine.setReplacementStrategy(new GenerationalReplacement());
        engine.setVerbose(true);

        System.out.println("Starting Knapsack Problem Optimization...\n");
        engine.run();
    }
}

