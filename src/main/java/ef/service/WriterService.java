package ef.service;

import ef.model.Writer;
import ef.repository.DatabaseRepoImpl.WriterRepoImpl;
import ef.repository.WriterRepository;

import java.util.List;

public class WriterService {
    private WriterRepository writerRepository = new WriterRepoImpl();

    public Writer getWriterByID(Long id) {
        return writerRepository.getById(id);
    }

    public Writer saveWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    public void deleteWriterById(Long id) {
        writerRepository.deleteById(id);
    }

    public Writer updateWriter(Writer writer) {
        return writerRepository.update(writer);
    }

    public List<Writer> getAllWriters() {
        return writerRepository.getAll();
    }
}
