package us.donut.skuniversal.advancedban.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("AdvancedBan - Punished Player")
@Description("Returns the punished player on Punishment event.")
public class ExprPunishedPlayer extends SimpleExpression<OfflinePlayer> {

    static {
        Skript.registerExpression(ExprPunishedPlayer.class, OfflinePlayer.class, ExpressionType.SIMPLE, "[the] [AdvancedBan] punished player");
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends OfflinePlayer> getReturnType() {
        return OfflinePlayer.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult pr) {
        if (!ScriptLoader.isCurrentEvent(PunishmentEvent.class)) {
            Skript.error("You can not use punished player expression in any event but on punish.");
            return false;
        }
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean b) {
        return "the punished player";
    }

    @Override
    @Nullable
    protected OfflinePlayer[] get(Event e) {
        return new OfflinePlayer[]{Bukkit.getOfflinePlayer(((PunishmentEvent) e).getPunishment().getName())};
    }
}
