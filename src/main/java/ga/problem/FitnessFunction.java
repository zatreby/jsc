package ga.problem;
import ga.chromosome.Chromosome;

@FunctionalInterface
public interface FitnessFunction {
    double evaluate(Chromosome chromosome);
}
