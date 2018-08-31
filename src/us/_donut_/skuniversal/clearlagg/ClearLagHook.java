package us._donut_.skuniversal.clearlagg;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.minebuilders.clearlag.events.EntityRemoveEvent;
import me.minebuilders.clearlag.events.TPSUpdateEvent;
import org.bukkit.World;
import us._donut_.skuniversal.SkUniversalEvent;

public class ClearLagHook {

    static {
        Skript.registerEvent("ClearLagg - Clear Entities", SkUniversalEvent.class, EntityRemoveEvent.class, "[ClearLag[g]] (remove|delete|clear) entities")
                .description("Called when ClearLagg cleared entities.\n\n" +
                        "**Event Expressions:**\n" +
                        "`[the] entities (cleared|removed|deleted) [by ClearLag[g]]`")
                .examples("on clear entities:", "\tlog \"[%now%] %entities cleared%\" to \"clearlagg.log\"");
        EventValues.registerEventValue(EntityRemoveEvent.class, World.class, new Getter<World, EntityRemoveEvent>() {
            public World get(EntityRemoveEvent e) {
                return e.getWorld();
            }
        }, 0);

        Skript.registerEvent("ClearLagg - TPS Update", SkUniversalEvent.class, TPSUpdateEvent.class, "[ClearLag[g]] TPS update")
                .description("Called when ClearLagg TPS is updated.")
                .examples("on TPS update:", "\tset {TPS} to event-number");
        EventValues.registerEventValue(TPSUpdateEvent.class, Double.class, new Getter<Double, TPSUpdateEvent>() {
            public Double get(TPSUpdateEvent e) {
                return e.getTPS();
            }
        }, 0);
    }

}