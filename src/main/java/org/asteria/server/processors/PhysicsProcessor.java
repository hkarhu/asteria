package org.asteria.server.processors;

import org.asteria.server.components.Physical;
import org.asteria.server.components.Position;
import org.entityflow.entity.Entity;
import org.entityflow.processors.EntityProcessorBase;
import org.flowutils.time.Time;

/**
 *
 */
public final class PhysicsProcessor extends EntityProcessorBase {

    public PhysicsProcessor() {
        super(Position.class, Physical.class);
    }

    @Override
    protected void processEntity(final Time time, final Entity entity) {
        // TODO> Physics
    }
}
