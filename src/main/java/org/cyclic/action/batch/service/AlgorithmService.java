package org.cyclic.action.batch.service;

import org.cyclic.action.batch.enumeration.Algorithm;
import org.cyclic.action.batch.model.Position;

import java.util.List;
import java.util.Map;

public interface AlgorithmService {
    Map<Algorithm, List<Position>> defineAlgorithmMap(final Position position, final List<Position> history);

    Algorithm getAlgorithm(final Map<Algorithm, List<Position>> algorithmMap);
}
