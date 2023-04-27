package org.cyclic.action.batch.enumeration;

import lombok.Getter;

import java.util.Optional;
import java.util.Set;

public enum Algorithm {
    FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5), SIXTH(6), ZERO(0);

    @Getter
    private final Integer number;

    Algorithm(final Integer number) {
        this.number = number;
    }

    public static boolean isAlgorithmExact(final Algorithm algorithm) {
        return Optional.ofNullable(algorithm)
                .map(alg -> Set.of(FIRST, SECOND, THIRD).contains(alg))
                .orElse(false);
    }
}
