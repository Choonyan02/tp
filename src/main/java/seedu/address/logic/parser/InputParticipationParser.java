package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_POINTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.InputParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;

/**
 * Parses input arguments and creates a new InputParticipationCommand object
 */
public class InputParticipationParser implements Parser<InputParticipationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code InputParticipationCommand}
     * and returns a {@code InputParticipationCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InputParticipationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL,
                        PREFIX_PARTICIPATION_POINTS);
        Index index;
        int tut = -1;
        int points = -1;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InputParticipationCommand.MESSAGE_USAGE), ive);
        }

        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            tut = ParserUtil.parseTutorial(argMultimap.getValue(PREFIX_TUTORIAL).get());
        }

        if (argMultimap.getValue(PREFIX_PARTICIPATION_POINTS).isPresent()) {
            points = ParserUtil.parseParticipationPoints(argMultimap.getValue(PREFIX_PARTICIPATION_POINTS).get());
        }

        if (tut == -1) {
            throw new ParseException(Attendance.TUTORIAL_ERROR_MSG);
        }

        if (points == -1) {
            throw new ParseException(Attendance.PARTICIPATION_ERROR_MSG);
        }

        return new InputParticipationCommand(index, Index.fromOneBased(tut), points);
    }
}