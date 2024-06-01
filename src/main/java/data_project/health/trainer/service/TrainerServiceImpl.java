package data_project.health.trainer.service;

import data_project.health.trainingClass.repository.TrainingRepository;
import data_project.health.global.exception.BusinessException;
import data_project.health.global.exception.errorcode.CommonErrorCode;
import data_project.health.trainer.dto.TrainerDtoReq;
import data_project.health.trainer.dto.TrainerDtoRes;
import data_project.health.trainer.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final TrainingRepository trainingRepository;

    @Override
    @Transactional
    public TrainerDtoRes.RentBookRes rentBook(TrainerDtoReq.rentBookReq request){
        //book이 대여 가능한지 확인
        if(trainerRepository.checkRentBook(request.getBook_id()).equals("NONAVAILABLE")){
            throw new BusinessException(CommonErrorCode.BOOk_NOT_RENT);
        }

        //rent_book 테이블에 대여 행 삽입
        Long rent_book_id = trainerRepository.rentBook(request);

        //책 대여 불가능으로 변경
        trainerRepository.changeRentBook(request.getBook_id(),"NONAVAILABLE");

        return trainerRepository.selectRentBook(rent_book_id);
    }

    @Override
    @Transactional
    public void returnBook(Long rent_book_id){
        //book_id 조회
        Long book_id = trainerRepository.getBookIdByRentBookId(rent_book_id);

        //rent_book_id 해당하는 행 삭제(대여 반납)
        int i = trainerRepository.returnBook(rent_book_id);
        if(i ==0){
            throw new BusinessException(CommonErrorCode.RENT_BOOk_NOT_FOUND);
        }

        //book_id에 해당하는 책을 대여가능 상태로 수정
        trainerRepository.changeRentBook(book_id,"AVAILABLE");

    }

    @Override
    @Transactional
    public void renewBook(Long rent_book_id){
        if(trainerRepository.renewBook(rent_book_id)==0){
            throw new BusinessException(CommonErrorCode.DATABASE_ERROR);
        }
    }



}
