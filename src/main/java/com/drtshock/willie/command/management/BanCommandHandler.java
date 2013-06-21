package com.drtshock.willie.command.management;

import com.drtshock.willie.Willie;
import com.drtshock.willie.command.CommandHandler;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 *
 * @author drtshock
 */
public class BanCommandHandler implements CommandHandler {

    @Override
    public void handle(Willie bot, Channel channel, User sender, String[] args) {
        if (args.length != 0) {
            if (channel.getOps().contains(sender)) {
                if (channel.getUsers().contains(bot.getUser(args[0]))) {
                    if (args.length == 1) {
                        bot.ban(channel, bot.getUser(args[0]).getHostmask());
                    } else {
                        StringBuilder sb = new StringBuilder();
                        for (String arg : args) {
                            if (!arg.equals(args[0])) {
                                sb.append(arg).append(" ");
                            }
                        }
                        String reason = sb.toString().trim();
                        bot.ban(channel, bot.getUser(args[0]).getHostmask());
                        bot.kick(channel, bot.getUser(args[0]), reason);
                    }
                } else {
                    bot.sendNotice(sender, "That user is not in the channel!");
                }
            } else {
                bot.sendNotice(sender, "You do not have permission to do that!");
            }
        } else {
            bot.sendNotice(sender, "Usage: !ban <user>");
        }
    }
}
