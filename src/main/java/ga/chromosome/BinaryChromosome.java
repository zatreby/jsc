package ga.chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinaryChromosome extends Chromosome {
    private List<Boolean> genes;

    public BinaryChromosome() {
        this.genes = new ArrayList<>();
    }

    public BinaryChromosome(List<Boolean> genes) {
        this.genes = new ArrayList<>(genes);
    }

    @Override
    public void initialize(int length, double lowerBound, double upperBound) {
        genes = new ArrayList<>(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            genes.add(random.nextBoolean());
        }
    }

    @Override
    public int getLength() {
        return genes.size();
    }

    @Override
    public Boolean getGene(int index) {
        return genes.get(index);
    }

    @Override
    public void setGene(int index, Object value) {
        genes.set(index, (Boolean) value);
    }

    @Override
    public List<Object> getGenes() {
        return new ArrayList<>(genes);
    }

    public void setGenes(List<Object> genes) {
        genes.forEach(gene -> this.genes.add((Boolean) gene));
    }

    @Override
    public BinaryChromosome clone() {
        BinaryChromosome clone = new BinaryChromosome(this.genes);
        clone.setFitness(this.fitness);
        return clone;
    }

    public int toInteger() {
        int value = 0;
        for (Boolean gene : genes) {
            if (gene) {
                value = (value << 1) | 1;
            } else {
                value = (value << 1);
            }
        }
        return value;
    }
}
