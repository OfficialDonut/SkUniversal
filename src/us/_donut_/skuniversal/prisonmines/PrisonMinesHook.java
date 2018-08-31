package us._donut_.skuniversal.prisonmines;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.lightshard.prisonmines.MineAPI;
import net.lightshard.prisonmines.event.mine.MinePostResetEvent;
import net.lightshard.prisonmines.event.mine.MinePreResetEvent;
import us._donut_.skuniversal.SkUniversalEvent;

public class PrisonMinesHook {

    public static MineAPI mineAPI = new MineAPI.PrisonMinesAPI();

    static {
        Skript.registerEvent("PrisonMine - Mine Reset Start", SkUniversalEvent.class, MinePreResetEvent.class, "[PrisonMines] mine reset (begin|start)")
                .description("Called when a mine begins to reset.")
                .examples("on mine reste begin:", "\tsend \"Mine %event-string% is resetting!\"");
        EventValues.registerEventValue(MinePreResetEvent.class, String.class, new Getter<String, MinePreResetEvent>() {
            public String get(MinePreResetEvent e) {
                return e.getMine().getName();
            }
        }, 0);

        Skript.registerEvent("PrisonMine - Mine Reset Finish", SkUniversalEvent.class, MinePostResetEvent.class, "[PrisonMines] mine reset (finish|end|complete)")
                .description("Called when a mine is done resetting.")
                .examples("on mine reset finish:", "\tsend \"Mine %event-string% is done resetting\"");
        EventValues.registerEventValue(MinePostResetEvent.class, String.class, new Getter<String, MinePostResetEvent>() {
            public String get(MinePostResetEvent e) {
                return e.getMine().getName();
            }
        }, 0);
    }

}
