package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.lightshard.prisonmines.event.mine.MinePostResetEvent;
import net.lightshard.prisonmines.event.mine.MinePreResetEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import us._donut_.skuniversal.SkUniversal;

public class PrisonMinesRegister {

    public static void registerPrisonMines() {
        if (Bukkit.getServer().getPluginManager().getPlugin("PrisonMines") != null) {
            SkUniversal.hookedPlugins.add("PrisonMines");
        }

        //Conditions
        Skript.registerCondition(CondTeleportLoc.class, "[the] [PrisonMines] mine [(named|with name)] %string% has [a] (teleport|tp) loc[ation]");
        Skript.registerCondition(CondResetting.class, "[the] [PrisonMines] mine [(named|with name)] %string% is (resetting|being reset)");

        //Effects
        Skript.registerEffect(EffReset.class, "reset [the] [PrisonMines] mine [(named|with name)] %string%");

        //Expressions
        Skript.registerExpression(ExprMines.class, String.class, ExpressionType.SIMPLE, "[the] [names of] [all [the]] [PrisonMines] mines");
        Skript.registerExpression(ExprMineAtLoc.class, String.class, ExpressionType.SIMPLE, "[the] [name of [the]] [PrisonMines] mine at %location%");
        Skript.registerExpression(ExprAmountMined.class, Number.class, ExpressionType.SIMPLE, "[the] (amount|number) of blocks mined in [the] [PrisonMines] mine [(named|with name)] %string%");
        Skript.registerExpression(ExprPercentMined.class, Number.class, ExpressionType.SIMPLE, "[the] percent[age] of [the] blocks [already] mined in [the] [PrisonMines] mine [(named|with name)] %string%");
        Skript.registerExpression(ExprPercentLeft.class, Number.class, ExpressionType.SIMPLE, "[the] percent[age] of [the] blocks (left|not mined) in [the] [PrisonMines] mine [(named|with name)] %string%");
        Skript.registerExpression(ExprTimeLeft.class, Number.class, ExpressionType.SIMPLE, "[the] [amount of] time [left] until [the] [PrisonMines] mine [(named|with name)] %string% (resets|is reset)");
        Skript.registerExpression(ExprTeleportLoc.class, Location.class, ExpressionType.SIMPLE, "[the] (teleport|tp) loc[ation] of [the] [PrisonMines] mine [(named|with name)] %string%");

        //Events
        Skript.registerEvent("Mine Reset Start", SimpleEvent.class, MinePreResetEvent.class, "[on] [PrisonMines] mine reset (begin|start)");
        EventValues.registerEventValue(MinePreResetEvent.class, String.class, new Getter<String, MinePreResetEvent>() {
            public String get(MinePreResetEvent e) {
                return e.getMine().getName();
            }
        }, 0);
        Skript.registerEvent("Mine Reset Finish", SimpleEvent.class, MinePostResetEvent.class, "[on] [PrisonMines] mine reset (finish|end|complete)");
        EventValues.registerEventValue(MinePostResetEvent.class, String.class, new Getter<String, MinePostResetEvent>() {
            public String get(MinePostResetEvent e) {
                return e.getMine().getName();
            }
        }, 0);
    }
}
