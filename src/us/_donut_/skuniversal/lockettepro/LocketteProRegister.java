package us._donut_.skuniversal.lockettepro;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;

public class LocketteProRegister {

    public static void registerLockettePro() {
        if (Bukkit.getServer().getPluginManager().getPlugin("LockettePro") != null) {

            //Conditions
            Skript.registerCondition(CondOwner.class, "%player% is [the] [LockettePro] owner of %block%", "%block%'s [LockettePro] owner is %player%");
            Skript.registerCondition(CondUser.class, "%player% is [a] [LockettePro] user of %block%");
            Skript.registerCondition(CondLocked.class, "%block% is (locked|protected) [by LockettePro]");
            Skript.registerCondition(CondLockable.class, "%block% is (lockable|able to be locked) [by LockettePro]");

            //Effects
            Skript.registerEffect(EffLock.class, "(put|place) [a] [LockettePro] sign on [the] %string% [block]face of %block% with %player% as [the] owner");
        }
    }
}