package guru.recipe.servicies;

import guru.recipe.command.UnitOfMeasureCommand;
import guru.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.recipe.domain.UnitOfMeasure;
import guru.recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Nikolay Horushko
 */
public class UnitOfMeasureServiceImplTest {

    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    private UnitOfMeasureService unitOfMeasureService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void listAllUnitOfMeasure() throws Exception {

        Set<UnitOfMeasure> uoms = new HashSet<>();

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        uom1.setDescription("each");

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        uom2.setDescription("tablespoon");

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setId(3L);
        uom3.setDescription("teaspoon");

        uoms.add(uom1);
        uoms.add(uom2);
        uoms.add(uom3);

        when(unitOfMeasureRepository.findAll()).thenReturn(uoms);

        //when
        Set<UnitOfMeasureCommand> result = unitOfMeasureService.listAllUnitOfMeasure();

        assertTrue(result.size() == 3);
        verify(unitOfMeasureRepository, times(1)).findAll();

    }

}