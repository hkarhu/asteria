package org.asteria.server.processors;

import org.asteria.server.components.Physical;
import org.asteria.server.components.Position;
import org.asteria.utils.Vec3d;
import org.entityflow.entity.Entity;
import org.entityflow.processors.EntityProcessorBase;
import org.flowutils.time.Time;

/**
 *
 */
public final class PhysicsProcessor extends EntityProcessorBase {

    private static final double DEFAULT_PHYSICS_TIME_STEP_S = 0.01;

    public PhysicsProcessor() {
        this(DEFAULT_PHYSICS_TIME_STEP_S);
    }

    public PhysicsProcessor(double physicsTimeStep_s) {
        super(physicsTimeStep_s, true, Position.class, Physical.class);
        setFixedTimeStep(true);
    }

    @Override
    protected void processEntity(final Time time, final Entity entity) {
        final double timeDelta_s = time.getSecondsSinceLastStep();

        final Position position = entity.get(Position.class);
        final Physical physical = entity.get(Physical.class);

        // Initialize previous pos if needed
        if (physical.previousPos == null) {
            physical.previousPos = new Vec3d(position.pos);
        }

        // Collect forces to apply

        // Update ve

        position.pos

    }
}
