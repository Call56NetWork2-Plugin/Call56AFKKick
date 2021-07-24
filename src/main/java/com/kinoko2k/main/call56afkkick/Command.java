package com.kinoko2k.main.call56afkkick;

import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String s, String[] args) {
        Player p = null;
        if(sender instanceof Player)
            p = (Player) sender;

        if (p != null && cmd.getName().equalsIgnoreCase("noafk")) {
            boolean sond_on_notafk = Boolean.parseBoolean(Main.getInstance().getConfig().getString("Config.sond_on_notafk"));
            if (sond_on_notafk == true) {
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10,3);
            }
            Main main = Main.getInstance();
            for (int i = 0; i < main.list.size(); i++) {
                if (p.getUniqueId() == main.list.get(i).getPlayer().getUniqueId()) {
                    main.list.get(i).resetMinute();
                    break;
                }
            }
            p.sendMessage("Â§7" + Main.getInstance().getConfig().getString("Config.prefix") + " " + Main.getInstance().getConfig().getString("Config.kick_not_afk_msg"));

            return true;
        } else if (cmd.getName().equalsIgnoreCase("afkreload")) {
            if (sender instanceof ConsoleCommandSender || p.hasPermission("call56afk.reload")) {
                Main.getInstance().reloadConfig();
                String config_values = Main.getInstance().getConfig().getString("Config.config_values");
                String config_reload = Main.getInstance().getConfig().getString("Config.config_reload");
                sender.sendMessage("" + config_values);
                String prefix = Main.getInstance().getConfig().getString("Config.prefix");
                String kick_msg = Main.getInstance().getConfig().getString("Config.kick_msg");
                Integer kick_warn_delay = Main.getInstance().getConfig().getInt("Config.kick_warn_delay");
                String kick_warn_msg = Main.getInstance().getConfig().getString("Config.kick_warn_msg");
                String kick_warn_msg_afk = Main.getInstance().getConfig().getString("Config.kick_warn_msg_afk");
                Integer kick_delay = Main.getInstance().getConfig().getInt("Config.kick_delay");
                String sound_on_get_warn = Main.getInstance().getConfig().getString("Config.sound_on_get_warn");
                String sound_on_notafk = Main.getInstance().getConfig().getString("Config.sond_on_notafk");
                String config_noperm = Main.getInstance().getConfig().getString("Config.config_noperm");
                String notafkmsg = Main.getInstance().getConfig().getString("Config.kick_not_afk_msg");
                sender.sendMessage("§7---------------------------------");
                sender.sendMessage("§3Prefix: §7" + prefix);
                sender.sendMessage("§3Kickメッセージ: §7" + kick_msg);
                sender.sendMessage("§3Kick警告遅延: §7" + kick_warn_delay);
                sender.sendMessage("§3Kick警告メッセージ: §7" + kick_warn_msg);
                sender.sendMessage("§3AFKは確認されていないメッセージ: §7" + kick_warn_msg_afk);
                sender.sendMessage("§3AFKは確認されていません: " + notafkmsg);
                sender.sendMessage("§3Kick遅延: §7" + kick_delay);
                sender.sendMessage("§3注意音: §7" + sound_on_get_warn);
                sender.sendMessage("§3確認時に音を鳴らす: §7" + sound_on_notafk);
                sender.sendMessage("§3No permisson: §7" + config_noperm);
                sender.sendMessage("§7---------------------------------");
                sender.sendMessage("");
                sender.sendMessage("" + config_reload);
            } else {
                sender.sendMessage("§7" + Main.getInstance().getConfig().getString("Config.config_noperm"));
            }
        }
        return true;
    }
}

