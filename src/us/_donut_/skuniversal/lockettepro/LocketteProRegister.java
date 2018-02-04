package us._donut_.skuniversal.lockettepro;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.OfflinePlayer;

public class LocketteProRegister {

    public LocketteProRegister() {
        //Conditions
        Skript.registerCondition(CondUser.class, "%player% is [a] [LockettePro] user of [the] %block%");
        Skript.registerCondition(CondLockedByLockettePro.class, "[the] %block% is (locked|protected) [by LockettePro]");
        Skript.registerCondition(CondLockable.class, "[the] %block% is (lockable|able to be locked) [by LockettePro]");

        //Effects
        Skript.registerEffect(EffLock.class, "(put|place) [a] [LockettePro] sign on [the] %string% [block]face of [the] %block% with %player% as [the] owner");

        //Expressions
        Skript.registerExpression(ExprLocketteProOwner.class, OfflinePlayer.class, ExpressionType.COMBINED, "[LockettePro] owner of [the] %block%", "[the] %block%'s [LockettePro] owner");
    }
}