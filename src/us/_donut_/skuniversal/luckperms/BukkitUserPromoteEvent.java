package us._donut_.skuniversal.luckperms;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BukkitUserPromoteEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private OfflinePlayer player;
    private String oldGroup;
    private String newGroup;

    public BukkitUserPromoteEvent(OfflinePlayer player, String oldGroup, String newGroup) {
        this.player = player;
        this.oldGroup = oldGroup;
        this.newGroup = newGroup;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public String getOldGroup() {
        return oldGroup;
    }

    public String getNewGroup() {
        return newGroup;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}