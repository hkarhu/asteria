package org.asteria.server.components;

import org.asteria.utils.Vec3d;
import org.entityflow.component.ComponentBase;

/**
 * A physical object in space.
 */
public final class Physical extends ComponentBase {

    public double mass = 1;

    // Use previous pos instead of velocity for Verlet integration
    public Vec3d previousPos = null;

    /**
     * Add any forces that act on the entity to this vector, it will be applied by the physics system when it updates.
     */
    public final Vec3d accumulatedForces = new Vec3d();

    public Physical(final double mass) {
        this.mass = mass;
    }

    // TODO: Other physics.

}
