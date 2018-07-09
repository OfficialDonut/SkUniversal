package us._donut_.skuniversal.lockette;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.OfflinePlayer;

public class LocketteRegister {
    public LocketteRegister() {
        //Conditions
        Skript.registerCondition(CondLockedByLockette.class, "[the] %block% is (locked|protected) [by Lockette]");

        //Expressions
        Skript.registerExpression(ExprLocketteOwner.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] [Lockette] owner of [the] %block%", "[the] %block%'s [Lockette] owner");
    }
}
