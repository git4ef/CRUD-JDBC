package ef.service;

import ef.model.Post;
import ef.model.Writer;
import ef.repository.DatabaseRepoImpl.WriterRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class WriterServiceTest {
    @InjectMocks
    WriterService writerService;
    @Mock
    WriterRepoImpl writerRepo;
    @Captor
    ArgumentCaptor<Writer> requestCaptorWriter = ArgumentCaptor.forClass(Writer.class);
    Writer writer;
    @BeforeEach
    void setup() {
        writer = new Writer(1L,"first name","last name");
    }

    @Test
    public void shouldGetWriterObject_whenSaveWriter() {
        when(writerService.saveWriter(writer)).thenReturn(writer);
        Writer resultWriter = writerService.saveWriter(writer);
        verify(writerRepo, times(1)).save(requestCaptorWriter.capture());
        assertEquals(writer,resultWriter);
    }

    @Test
    public void shouldGetExistWriterObject_whenFindWriterByID() {
        writerService.saveWriter(writer);
        when(writerService.getWriterByID(1L)).thenReturn(writer);
        Writer expectedWriter = writerService.getWriterByID(1L);
        verify(writerRepo, times(1)).getById(anyLong());
        assertEquals(writer,expectedWriter);
    }

    @Test
    public void shouldDoNothing_whenDeleteWriterByID() {
        doNothing().when(writerRepo).deleteById(1L);
        writerRepo.save(writer);
        writerRepo.deleteById(1L);
        assertTrue(writerRepo.getAll().isEmpty());
        verify(writerRepo,times(1)).deleteById(1L);
    }

    @Test
    public void shouldGetUpdatedWriterObject_whenUpdateWriterByID() {
        when(writerService.updateWriter(any())).thenReturn(writer);
        writer.setFirstName("update firstname");
        writer.setLastName("update lastname");
        Writer expectedWriter = writerService.updateWriter(writer);
        verify(writerRepo, times(1)).update(any());
        assertEquals(expectedWriter.getFirstName(), "update firstname");
        assertEquals(expectedWriter.getLastName(), "update lastname");
    }

    @Test
    public void shouldGetAllWriter(){
        writerService.saveWriter(writer);
        when(writerService.getAllWriters()).thenReturn(List.of(writer));
        List<Writer> writers = writerService.getAllWriters();
        verify(writerRepo,times(1)).getAll();
        assertEquals(writers.size(),1);
    }
}
