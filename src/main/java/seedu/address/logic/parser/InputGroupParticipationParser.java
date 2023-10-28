package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION_POINTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.InputGroupParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Group;

/**
 * Parses input arguments and creates a new InputGroupParticipationCommand object
 */
public class InputGroupParticipationParser implements Parser<InputGroupParticipationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code InputGroupParticipationCommand}
     * and returns a {@code InputGroupParticipationCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InputGroupParticipationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL,
                PREFIX_PARTICIPATION_POINTS);
        Group group;
        int tut = -1;
        int points = -1;

        try {
            group = ParserUtil.parseGroup(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InputGroupParticipationCommand.MESSAGE_USAGE), ive);
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

        return new InputGroupParticipationCommand(group, Index.fromOneBased(tut), points);
    }
}