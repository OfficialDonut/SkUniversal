package us._donut_.skuniversal.luckperms;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.event.EventBus;
import me.lucko.luckperms.api.event.user.track.UserDemoteEvent;
import me.lucko.luckperms.api.event.user.track.UserPromoteEvent;
import org.bukkit.Bukkit;

class LuckPermsListener {

    LuckPermsListener() {
        EventBus eventBus = LuckPerms.getApi().getEventBus();

        eventBus.subscribe(UserPromoteEvent.class, this::onPromote);
        eventBus.subscribe(UserDemoteEvent.class, this::onDemote);
    }

    private void onPromote(UserPromoteEvent event) {
        String oldGroup;
        String newGroup;
        if (event.getGroupFrom().isPresent()) {
            oldGroup = event.getGroupFrom().get();
        } else {
            oldGroup = "<none>";
        }
        if (event.getGroupTo().isPresent()) {
            newGroup = event.getGroupFrom().get();
        } else {
            newGroup = "<none>";
        }
        if (event.getGroupFrom().isPresent()) {
            BukkitUserPromoteEvent bukkitUserPromoteEvent = new BukkitUserPromoteEvent(Bukkit.getOfflinePlayer(event.getUser().getUuid()), oldGroup, newGroup);
            Bukkit.getServer().getPluginManager().callEvent(bukkitUserPromoteEvent);
        }
    }

    private void onDemote(UserDemoteEvent event) {
        String oldGroup;
        String newGroup;
        if (event.getGroupFrom().isPresent()) {
            oldGroup = event.getGroupFrom().get();
        } else {
            oldGroup = "<none>";
        }
        if (event.getGroupTo().isPresent()) {
            newGroup = event.getGroupFrom().get();
        } else {
            newGroup = "<none>";
        }
        if (event.getGroupFrom().isPresent()) {
            BukkitUserDemoteEvent bukkitUserDemoteEvent = new BukkitUserDemoteEvent(Bukkit.getOfflinePlayer(event.getUser().getUuid()), oldGroup, newGroup);
            Bukkit.getServer().getPluginManager().callEvent(bukkitUserDemoteEvent);
        }
    }

}