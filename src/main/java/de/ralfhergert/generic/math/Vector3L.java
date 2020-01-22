package de.ralfhergert.generic.math;

public class Vector3L {

    private long[] v = new long[]{0, 0, 0};

    public Vector3L(long x, long y, long z) {
        v = new long[]{x, y, z};
    }

    public Vector3L sub(Vector3L r) {
        return new Vector3L(v[0] - r.v[0], v[1] - r.v[1], v[2] - r.v[2]);
    }

    public double length() {
        return Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
    }
}
