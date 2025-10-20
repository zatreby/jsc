package ga.operators.mutation;

import ga.chromosome.Chromosome;

public interface MutationOperator {
    void mutate(Chromosome chromosome);
}