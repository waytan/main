package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {

    public static final Module TYPICAL_MODULE_ONE = new ModuleBuilder().withModuleCode("CS1101S")
            .withModuleTitle("Programming Methodology I").withAcademicYear("1819")
            .withSemester("1").build();
    public static final Module TYPICAL_MODULE_TWO = new ModuleBuilder().withModuleCode("CS1231")
            .withModuleTitle("Discrete Structures").withAcademicYear("1819")
            .withSemester("1")
            .withTags("proving").build();
    public static final Module TYPICAL_MODULE_THREE = new ModuleBuilder().withModuleCode("CS2103T")
            .withModuleTitle("Software Engineering").withAcademicYear("1819")
            .withSemester("1")
            .withTags("project").build();
    public static final Module TYPICAL_MODULE_FOUR = new ModuleBuilder().withModuleCode("CS3233")
            .withModuleTitle("Competitive Programming").withAcademicYear("1920")
            .withSemester("2")
            .withTags("difficult").build();
    public static final Module TYPICAL_MODULE_FIVE = new ModuleBuilder().withModuleCode("MA1101R")
            .withModuleTitle("Linear Algebra I").withAcademicYear("1718")
            .withSemester("1")
            .withTags("matrices").build();
    public static final Module TYPICAL_MODULE_SIX = new ModuleBuilder().withModuleCode("GER1000")
            .withModuleTitle("Quantitative Reasoning").withAcademicYear("1516")
            .withSemester("2")
            .withTags("Statistics").build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical modules.
     */
    public static AddressBook getTypicalModulesAddressBook() {
        AddressBook ab = new AddressBook();
        for (Module module : getTypicalModules()) {
            ab.addModule(module);
        }
        return ab;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(TYPICAL_MODULE_ONE, TYPICAL_MODULE_TWO,
                TYPICAL_MODULE_THREE, TYPICAL_MODULE_FOUR, TYPICAL_MODULE_FIVE, TYPICAL_MODULE_SIX));
    }
}
