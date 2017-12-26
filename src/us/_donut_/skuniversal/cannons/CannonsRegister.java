package us._donut_.skuniversal.cannons;

import at.pavlov.cannons.event.*;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.LivingEntity;
import us._donut_.skuniversal.SkUniversalEvent;

public class CannonsRegister {

    public static void registerCannons() {
        //Conditions
        Skript.registerCondition(CondReadyToFire.class, "[the] cannon [with [the] id [of]] %string% is ready to fire");
        Skript.registerCondition(CondClean.class, "[the] cannon [with id] %string% is clean");
        Skript.registerCondition(CondProjectileLoaded.class, "[a] projectile is loaded in [the] cannon [with [the] id [of]] %string%");
        Skript.registerCondition(CondProjectilePushed.class, "[the] projectile in [the] cannon [with [the] id [of]] %string% (is|has been) pushed");
        Skript.registerCondition(CondOverheated.class, "[the] cannon [with [the] id [of]] %string% is overheated");

        //Effects
        Skript.registerEffect(EffDestroyCannon.class, "destroy [the] cannon [with [the] id [of]] %string%[,] break blocks %boolean%[,] can explode %boolean%");
        Skript.registerEffect(EffFireCannon.class, "fire [the] cannon [with [the] id [of]] %string%[,] auto[ ]load %boolean%[,] (use|consume) ammo %boolean%");

        //Expressions
        Skript.registerExpression(CannonIDFromLoc.class, String.class, ExpressionType.SIMPLE, "[the] id of [the] cannon at %location%");
        Skript.registerExpression(AllCannonIDs.class, String.class, ExpressionType.SIMPLE, "[the] ids of all cannons");
        Skript.registerExpression(CannonIDsInBox.class, String.class, ExpressionType.SIMPLE, "[the] ids of [all] [the] cannons [with]in [the] box centered at %location% with length %number%[,] width %number%[,] [and] height %number%");
        Skript.registerExpression(CannonIDsInSphere.class, String.class, ExpressionType.SIMPLE, "[the] ids of [all] [the] cannons [with]in [the] sphere centered at %location% with radius %number%");
        Skript.registerExpression(CannonOwner.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] owner of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonLoc.class, Location.class, ExpressionType.SIMPLE, "[the] loc[ation] of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonDesign.class, String.class, ExpressionType.SIMPLE, "[the] design [type] [name] of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonName.class, String.class, ExpressionType.SIMPLE, "[the] name of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonSoot.class, Number.class, ExpressionType.SIMPLE, "[the] [amount of] soot in [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonTemp.class,Number.class, ExpressionType.SIMPLE, "[the] temp[erature] of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonCritTemp.class,Number.class, ExpressionType.SIMPLE, "[the] critical temp[erature] of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonFuse.class,Number.class, ExpressionType.SIMPLE, "[the] critical temp[erature] of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonLoadTime.class,Number.class, ExpressionType.SIMPLE, "[the] load[ing] time of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonMass.class,Number.class, ExpressionType.SIMPLE, "[the] mass of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonPitch.class, Number.class, ExpressionType.SIMPLE, "[the] [aiming] pitch of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonYaw.class, Number.class, ExpressionType.SIMPLE, "[the] [aiming] yaw of [the] cannon [with [the] id [of]] %string%");
        Skript.registerExpression(CannonLimit.class, Number.class, ExpressionType.COMBINED, "[the] cannon limit of %player%", "%player%'s cannon limit");
        Skript.registerExpression(CannonAmount.class, Number.class, ExpressionType.SIMPLE, "[the] (amount|number) of cannons %offlineplayer% has built");

        //Events
        Skript.registerEvent("Cannon Fire", SkUniversalEvent.class, CannonFireEvent.class, "cannon (fire|shoot)");
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
        Skript.registerEvent("Cannon Destroy", SkUniversalEvent.class, CannonDestroyedEvent.class, "cannon destroy");
        EventValues.registerEventValue(CannonDestroyedEvent.class, String.class, new Getter<String, CannonDestroyedEvent>() {
            public String get(CannonDestroyedEvent e) {
                return e.getCannon().getUID().toString();
            }
        }, 0);
        Skript.registerEvent("Projectile Impact", SkUniversalEvent.class, ProjectileImpactEvent.class, "cannon projectile impact");
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
        Skript.registerEvent("Cannon Pre-Creation", SkUniversalEvent.class, CannonBeforeCreateEvent.class, "cannon pre[-]creat(e|ion)");
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
        Skript.registerEvent("Cannon Create", SkUniversalEvent.class, CannonAfterCreateEvent.class, "cannon creat(e|ion)");
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
        Skript.registerEvent("Cannon Death", SkUniversalEvent.class, CannonsEntityDeathEvent.class, "(cannon death|death by cannon)");
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
        Skript.registerEvent("Cannon Use", SkUniversalEvent.class, CannonUseEvent.class, "cannon (use|[inter]action)");
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