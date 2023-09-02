package org.cyclic.action.batch.service;

import org.apache.commons.math3.util.Pair;
import org.cyclic.action.batch.enumeration.Algorithm;
import org.cyclic.action.batch.model.Position;

import java.util.List;

public interface AlgorithmService {

    Pair<Algorithm, List<Position>> definePositionsByAlgorithm(final Position position, final List<Position> history);
}
