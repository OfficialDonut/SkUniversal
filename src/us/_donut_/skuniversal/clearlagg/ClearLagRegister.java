package us._donut_.skuniversal.clearlagg;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.minebuilders.clearlag.events.EntityRemoveEvent;
import me.minebuilders.clearlag.events.TPSUpdateEvent;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import us._donut_.skuniversal.SkUniversalEvent;

public class ClearLagRegister {

    public static void registerClearLagg() {
        //Expressions
        Skript.registerExpression(ExprClearedEntities.class, Entity.class, ExpressionType.SIMPLE, "[the] entities (cleared|removed|deleted) [by ClearLag[g]]");

        //Events
        Skript.registerEvent("ClearLag Remove Entities", SkUniversalEvent.class, EntityRemoveEvent.class, "[ClearLag[g]] (remove|delete) entities");
        EventValues.registerEventValue(EntityRemoveEvent.class, World.class, new Getter<World, EntityRemoveEvent>() {
            public World get(EntityRemoveEvent e) {
                return e.getWorld();
            }
        }, 0);
        Skript.registerEvent("ClearLag TPS Update", SkUniversalEvent.class, TPSUpdateEvent.class, "[ClearLag[g]] TPS update");
        EventValues.registerEventValue(TPSUpdateEvent.class, Double.class, new Getter<Double, TPSUpdateEvent>() {
            public Double get(TPSUpdateEvent e) {
                return e.getTPS();
            }
        }, 0);
    }
}