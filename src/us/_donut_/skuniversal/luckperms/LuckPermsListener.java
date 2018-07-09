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
        String oldGroup = event.getGroupFrom().orElse(null);
        String newGroup = event.getGroupTo().orElse(null);
        BukkitUserPromoteEvent bukkitUserPromoteEvent = new BukkitUserPromoteEvent(Bukkit.getOfflinePlayer(event.getUser().getUuid()), oldGroup, newGroup);
        Bukkit.getServer().getPluginManager().callEvent(bukkitUserPromoteEvent);
    }

    private void onDemote(UserDemoteEvent event) {
        String oldGroup = event.getGroupFrom().orElse(null);
        String newGroup = event.getGroupTo().orElse(null);
        BukkitUserDemoteEvent bukkitUserDemoteEvent = new BukkitUserDemoteEvent(Bukkit.getOfflinePlayer(event.getUser().getUuid()), oldGroup, newGroup);
        Bukkit.getServer().getPluginManager().callEvent(bukkitUserDemoteEvent);
    }

}