package org.jankgg.slp.driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jankgg.slp.SlippiGame;

/**
 * The dopest (and only) java slippi replay parser around
 *
 */
public class SlippiJoe {
	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		Options options = new Options();
		Option option = new Option("f", "file", true, "File(s) to parse");
		option.setArgs(Option.UNLIMITED_VALUES);
		options.addOption(option);
		option = new Option("d", "dir", true, "Directory(s) containing .slp files to parse");
		option.setArgs(Option.UNLIMITED_VALUES);
		options.addOption(option);
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		try {
			CommandLine cmd = parser.parse(options, args);
			List<File> files = new ArrayList<File>();
			if (cmd.hasOption("f")) {
				for (String fileName : cmd.getOptionValues("f")) {
					files.add(new File(fileName));
				}
			}
			if (cmd.hasOption("d")) {
				for (String directoryName : cmd.getOptionValues("d")) {
					File directory = new File(directoryName);
					File filesList[] = directory.listFiles();
					for (File file : filesList) {
						if (file.getName().endsWith(".slp")) {
							files.add(file);
						}
					}
				}
			}
			if (files.isEmpty()) {
				System.out.println("No files provided for parsing.");
				formatter.printHelp("java -jar SlippiJoe.jar", options);
				return;
			}
			Instant start = Instant.now();
			List<SlippiGame> games = Collections.synchronizedList(new ArrayList<SlippiGame>());
			files.parallelStream().forEach(f -> {
				try {
					SlippiGame game = new SlippiGame(f);

					System.out.println(game.getMetadata().toString());
					System.out.println("Version: " + game.getGameStartEvent().getVersionString());
					System.out.println("Stage: " + game.getGameStartEvent().getGameInfoBlock().getStage().toString());
					System.out.println("Teams: " + game.getGameStartEvent().getGameInfoBlock().isTeams());
					System.out.println("Online: " + game.getGameStartEvent().isOnline());

					games.add(game);
				} catch (IOException e) {
					System.out.println("Failed to parse file: " + f.getAbsolutePath());
					try {
            Files.move(f.toPath(), new File("C:/bad_slippi/" + f.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
          } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
					e.printStackTrace();
				}
			});
			Instant end = Instant.now();
			System.out.println("Parsed  " + games.size() + "/" + files.size() + " slippi files in "
					+ Duration.between(start, end).toMillis() + "ms");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("java -jar SlippiJoe.jar", options);
		}

	}

}