package ga.chromosome;

import java.util.List;

public abstract class Chromosome implements Cloneable {
    protected double fitness;

    public abstract void initialize(int length, double lowerBound, double upperBound);
    public abstract int getLength();
    public abstract Object getGene(int index);
    public abstract List<Object> getGenes();
    public abstract void setGene(int index, Object value);
    public abstract void setGenes(List<Object> genes);
    public abstract Chromosome clone();

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < getLength(); i++) {
            sb.append(getGene(i));
            if (i < getLength() - 1) sb.append(", ");
        }
        sb.append("] Fitness: ").append(String.format("%.4f", fitness));
        return sb.toString();
    }
}

