package us.donut.skuniversal.cannons;

import at.pavlov.cannons.Cannons;
import at.pavlov.cannons.event.*;
import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import us.donut.skuniversal.SkUniversalEvent;

public class CannonsHook {

    public static Cannons cannons = Cannons.getPlugin();

    static {
        Skript.registerEvent("Cannons - Cannon Fire", SkUniversalEvent.class, CannonFireEvent.class, "cannon (fire|shoot)")
                .description("Called when a cannon shoots.")
                .examples("on cannon shoot:", "\tbroadcast \"%player% shot a cannon!\"");
        EventValues.registerEventValue(CannonFireEvent.class, String.class, new Getter<String, CannonFireEvent>() {
            public String get(CannonFireEvent e) {
                return e.getCannon().getUID().toString();
            }
        }, 0);
        EventValues.registerEventValue(CannonFireEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, CannonFireEvent>() {
            public OfflinePlayer get(CannonFireEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayer());
            }
        }, 0);

        Skript.registerEvent("Cannons - Cannon Destroy", SkUniversalEvent.class, CannonDestroyedEvent.class, "cannon destroy")
                .description("Called when a cannon is destroyed.")
                .examples("on cannon destroy:", "\tbroadcast \"%player% destroyed a cannon!\"");
        EventValues.registerEventValue(CannonDestroyedEvent.class, String.class, new Getter<String, CannonDestroyedEvent>() {
            public String get(CannonDestroyedEvent e) {
                return e.getCannon().getUID().toString();
            }
        }, 0);

        Skript.registerEvent("Cannons - Projectile Impact", SkUniversalEvent.class, ProjectileImpactEvent.class, "cannon projectile impact")
                .description("Called when a cannon projectile lands.")
                .examples("on cannon projectile impact:", "\tbroadcast \"A cannonball landed at %event-location%!\"");
        EventValues.registerEventValue(ProjectileImpactEvent.class, Location.class, new Getter<Location, ProjectileImpactEvent>() {
            public Location get(ProjectileImpactEvent e) {
                return e.getImpactLocation();
            }
        }, 0);
        EventValues.registerEventValue(ProjectileImpactEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, ProjectileImpactEvent>() {
            public OfflinePlayer get(ProjectileImpactEvent e) {
                return Bukkit.getOfflinePlayer(e.getShooterUID());
            }
        }, 0);

        Skript.registerEvent("Cannons - Cannon Pre-Creation", SkUniversalEvent.class, CannonBeforeCreateEvent.class, "cannon pre[-]creat(e|ion)")
                .description("Called right before a cannon is created.")
                .examples("on cannon pre-creation:", "\tbroadcast \"%player% is about to create a cannon!\"");
        EventValues.registerEventValue(CannonBeforeCreateEvent.class, String.class, new Getter<String, CannonBeforeCreateEvent>() {
            public String get(CannonBeforeCreateEvent e) {
                return e.getCannon().getUID().toString();
            }
        }, 0);
        EventValues.registerEventValue(CannonBeforeCreateEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, CannonBeforeCreateEvent>() {
            public OfflinePlayer get(CannonBeforeCreateEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayer());
            }
        }, 0);

        Skript.registerEvent("Cannons - Cannon Create", SkUniversalEvent.class, CannonAfterCreateEvent.class, "cannon creat(e|ion)")
                .description("Called when a cannon is created.")
                .examples("on cannon creation:", "\tbroadcast \"%player% created a cannon!\"");
        EventValues.registerEventValue(CannonAfterCreateEvent.class, String.class, new Getter<String, CannonAfterCreateEvent>() {
            public String get(CannonAfterCreateEvent e) {
                return e.getCannon().getUID().toString();
            }
        }, 0);
        EventValues.registerEventValue(CannonAfterCreateEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, CannonAfterCreateEvent>() {
            public OfflinePlayer get(CannonAfterCreateEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayer());
            }
        }, 0);

        Skript.registerEvent("Cannons - Cannon Death", SkUniversalEvent.class, CannonsEntityDeathEvent.class, "(cannon death|death by cannon)")
                .description("Called when someone dies from a cannon.")
                .examples("on death by cannon:", "\tbroadcast \"%event-entity% was killed by cannon!\"");
        EventValues.registerEventValue(CannonsEntityDeathEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, CannonsEntityDeathEvent>() {
            public OfflinePlayer get(CannonsEntityDeathEvent e) {
                return Bukkit.getOfflinePlayer(e.getShooter());
            }
        }, 0);
        EventValues.registerEventValue(CannonsEntityDeathEvent.class, LivingEntity.class, new Getter<LivingEntity, CannonsEntityDeathEvent>() {
            public LivingEntity get(CannonsEntityDeathEvent e) {
                return e.getKilledEntity();
            }
        }, 0);
        EventValues.registerEventValue(CannonsEntityDeathEvent.class, String.class, new Getter<String, CannonsEntityDeathEvent>() {
            public String get(CannonsEntityDeathEvent e) {
                return e.getCannonID().toString();
            }
        }, 0);

        Skript.registerEvent("Cannons - Cannon Use", SkUniversalEvent.class, CannonUseEvent.class, "cannon (use|[inter]action)")
                .description("Called when a player interacts with a cannon.")
                .examples("on cannon interaction:", "\tbroadcast \"%player% interacted with a cannon!\"");
        EventValues.registerEventValue(CannonUseEvent.class, String.class, new Getter<String, CannonUseEvent>() {
            public String get(CannonUseEvent e) {
                return e.getCannon().getUID().toString();
            }
        }, 0);
        EventValues.registerEventValue(CannonUseEvent.class, OfflinePlayer.class, new Getter<OfflinePlayer, CannonUseEvent>() {
            public OfflinePlayer get(CannonUseEvent e) {
                return Bukkit.getOfflinePlayer(e.getPlayer());
            }
        }, 0);
    }

}