package ga.core;

import ga.chromosome.Chromosome;

import java.util.*;
import java.util.stream.Collectors;

public record Population(List<Chromosome> chromosomes) {
    public Population(List<Chromosome> chromosomes) {
        this.chromosomes = new ArrayList<>(chromosomes);
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public int size() {
        return chromosomes.size();
    }

    public Chromosome get(int index) {
        return chromosomes.get(index);
    }

    public Chromosome getBest() {
        return chromosomes.stream()
                .max(Comparator.comparingDouble(Chromosome::getFitness))
                .orElseThrow();
    }

    public Chromosome getWorst() {
        return chromosomes.stream()
                .min(Comparator.comparingDouble(Chromosome::getFitness))
                .orElseThrow();
    }

    public double getAverageFitness() {
        return chromosomes.stream()
                .mapToDouble(Chromosome::getFitness)
                .average()
                .orElse(0.0);
    }

    public double getTotalFitness() {
        return chromosomes.stream()
                .mapToDouble(Chromosome::getFitness)
                .sum();
    }

    public List<Chromosome> getTopN(int n) {
        return chromosomes.stream()
                .sorted(Comparator.comparingDouble(Chromosome::getFitness).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}
