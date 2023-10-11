package org.softuni.exam.structures;

import org.softuni.exam.entities.Deliverer;
import org.softuni.exam.entities.Package;

import java.util.*;
import java.util.stream.Collectors;

public class DeliveriesManagerImpl implements DeliveriesManager {
    private Map<String,Deliverer> delivererById = new LinkedHashMap<>();
    private Map<String,Package> packegesById = new LinkedHashMap<>();
    private Map<String,Package> unassignedPackages = new LinkedHashMap<>();
    private Map<String,Integer> packegesByDeliverer = new LinkedHashMap<>();

    @Override
    public void addDeliverer(Deliverer deliverer) {
        delivererById.put(deliverer.getId(),deliverer);
        packegesByDeliverer.put(deliverer.getId(),0);
    }

    @Override
    public void addPackage(Package _package) {
        packegesById.put(_package.getId(),_package);
        unassignedPackages.put(_package.getId(),_package);
    }

    @Override
    public boolean contains(Deliverer deliverer) {
        return delivererById.get(deliverer.getId()) != null;
    }

    @Override
    public boolean contains(Package _package) {
        return packegesById.get(_package.getId()) != null;

    }

    @Override
    public Iterable<Deliverer> getDeliverers() {
        return delivererById.values();
    }

    @Override
    public Iterable<Package> getPackages() {
        return packegesById.values();
    }

    @Override
    public void assignPackage(Deliverer deliverer, Package _package) throws IllegalArgumentException {
        if(!contains(deliverer) || !contains(_package) ){
            throw new IllegalArgumentException();
        }
        int cuurentCount = packegesByDeliverer.get(deliverer.getId());
        packegesByDeliverer.put(deliverer.getId(),cuurentCount + 1);
        unassignedPackages.remove(_package.getId());
    }

    @Override
    public Iterable<Package> getUnassignedPackages() {

        return unassignedPackages.values();
    }

    @Override
    public Iterable<Package> getPackagesOrderedByWeightThenByReceiver() {
        return packegesById.values().stream().sorted(Comparator.comparing(Package::getWeight).reversed().thenComparing(Package::getReceiver))
                .collect(Collectors.toList());

    }

    @Override
    public Iterable<Deliverer> getDeliverersOrderedByCountOfPackagesThenByName() {

        return delivererById.values().stream()
                .sorted(Comparator.comparing((Deliverer d) -> packegesByDeliverer.get(d.getId())).reversed().thenComparing(Deliverer::getName))
                .collect(Collectors.toList());
    }
}
