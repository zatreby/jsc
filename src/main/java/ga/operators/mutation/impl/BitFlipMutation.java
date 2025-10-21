package ga.operators.mutation.impl;

import ga.chromosome.BinaryChromosome;
import ga.chromosome.Chromosome;
import ga.operators.mutation.MutationOperator;

import java.util.Random;

public class BitFlipMutation implements MutationOperator {
    @Override
    public void mutate(Chromosome chromosome) {
        if (chromosome instanceof BinaryChromosome binary) {
            Random random = new Random();
            int index = random.nextInt(binary.getLength());
            boolean currentBit = binary.getGene(index);
            binary.setGene(index, !currentBit);
        }
    }
}
