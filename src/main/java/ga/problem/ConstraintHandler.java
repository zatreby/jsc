package ga.problem;

import ga.chromosome.Chromosome;

public interface ConstraintHandler {
    boolean isInfeasible(Chromosome chromosome);

    default double getPenalizedFitness(Chromosome chromosome, FitnessFunction fitnessFunction, Double penalty) {
        if (penalty == null) {
            penalty = 0.1;
        }
        return fitnessFunction.evaluate(chromosome) * penalty;
    }
}
