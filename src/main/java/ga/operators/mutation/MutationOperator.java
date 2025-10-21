package ga.operators.mutation;

import ga.chromosome.Chromosome;

@FunctionalInterface
public interface MutationOperator {
    void mutate(Chromosome chromosome);
}