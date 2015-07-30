package org.asteria.utils;

import static java.lang.Math.*;

/**
 * Three dimensional vector that uses doubles to store the location.
 * LibGDX includes float based 3D vector classes, but for astronomical scales in a
 * semi-realistic solar system double precision is needed.
 */
public final class Vec3d {

    public double x = 0;
    public double y = 0;
    public double z = 0;

    public Vec3d() {
    }

    public Vec3d(Vec3d soure) {
        set(soure);
    }

    public Vec3d(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Sets all coordinates to zero.
     */
    public void clear() {
        x = 0;
        y = 0;
        z = 0;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    public void set(final Vec3d soure) {
        set(soure.x,
            soure.y,
            soure.z);
    }

    public void set(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void addScaled(Vec3d other, double otherScale) {
        x += other.x * otherScale;
        y += other.y * otherScale;
        z += other.z * otherScale;
    }

    public void add(Vec3d other) {
        x += other.x;
        y += other.y;
        z += other.z;
    }

    public void sub(Vec3d other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
    }

    public void mul(Vec3d other) {
        x *= other.x;
        y *= other.y;
        z *= other.z;
    }

    public void mul(double scale) {
        x *= scale;
        y *= scale;
        z *= scale;
    }

    public void div(Vec3d other) {
        x /= other.x;
        y /= other.y;
        z /= other.z;
    }

    public void div(double d) {
        x /= d;
        y /= d;
        z /= d;
    }

    public double lengthSquared() {
        return x*x + y*y + z*z;
    }

    public double length() {
        return sqrt(x * x + y * y + z * z);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Vec3d vec3d = (Vec3d) o;

        if (Double.compare(vec3d.x, x) != 0) return false;
        if (Double.compare(vec3d.y, y) != 0) return false;
        return Double.compare(vec3d.z, z) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Vec3d{" +
               "x=" + x +
               ", y=" + y +
               ", z=" + z +
               '}';
    }
}
