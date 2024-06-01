package data_project.health.trainingClass.service;

import data_project.health.trainingClass.converter.TrainingConverter;
import data_project.health.trainingClass.dto.TrainingDtoReq;
import data_project.health.trainingClass.dto.TrainingDtoRes;
import data_project.health.trainingClass.repository.TrainingRepository;
import data_project.health.global.exception.BusinessException;
import data_project.health.global.exception.errorcode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;


    @Override
    @Transactional
    public TrainingDtoRes.bookIdRes bookEnroll(TrainingDtoReq.bookEnroll request){
        Long book_id = trainingRepository.bookEnroll(request);

        return TrainingConverter.BookId(book_id);
    }

    @Override
    @Transactional
    public void bookUpdate(TrainingDtoReq.bookUpdate request) {
        int id = trainingRepository.bookUpdate(request);
        if (id == 0) {
            throw new BusinessException(CommonErrorCode.BOOk_NOT_FOUND);
        }
    }
    @Override
    @Transactional
    public void bookDelete(Long bookId){
        int id = trainingRepository.bookDelte(bookId);
        if (id == 0) {
            throw new BusinessException(CommonErrorCode.BOOk_NOT_FOUND);
        }
    }


    @Override
    public TrainingDtoRes.searchBookList bookSearch(){
        List<TrainingDtoRes.BookRes> booklist = trainingRepository.searchBook();

        return TrainingConverter.BookList(booklist);
    }

    @Override
    public TrainingDtoRes.searchBookList bookRentSearch(){
        List<TrainingDtoRes.BookRes> bookRentlist = trainingRepository.searchRentBook();

        return TrainingConverter.BookList(bookRentlist);
    }

}
