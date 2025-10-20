package ga.operators.selection;

import ga.chromosome.Chromosome;
import ga.core.Population;

import java.util.List;

public interface SelectionOperator {
    List<Chromosome> select(Population population, int numberOfParents);
}
