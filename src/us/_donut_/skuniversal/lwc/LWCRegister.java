package us._donut_.skuniversal.lwc;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.OfflinePlayer;

public class LWCRegister {

    public LWCRegister() {
        //Conditions
        Skript.registerCondition(CondProtected.class, "[the] %block% is (locked|protected) [by LWC]");
        Skript.registerCondition(CondAccess.class, "[the] %player% (has access to|can access) [LWC] [(locked|protected)]] [the] %block%");
        Skript.registerCondition(CondProtectable.class, "[the] %block% is [LWC] (lockable|protectable)");

        //Effects
        Skript.registerEffect(EffRemoveProtection.class, "(remove|delete) [the] [LWC] (protection|lock) from [the] %block%");

        //Expressions
        Skript.registerExpression(ExprLWCOwner.class, OfflinePlayer.class, ExpressionType.COMBINED, "[the] [LWC] owner of [the] %block%", "%block%'s [LWC] owner");
        Skript.registerExpression(ExprProtectionNumber.class, Number.class, ExpressionType.COMBINED, "[the] [(number|amount) of] [LWC] protections of %player%", "%player%'s [(number|amount) of] [LWC] protections");
    }
}