package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>(); // 실제 DB대신 가상의 DB를 map자료구조로 생성

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return null;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        // Map To List
        List<MemoResponseDto> responseList = memoList.values().stream()
                .map(MemoResponseDto::new).toList();
        // .values() -> Map에 담긴 모든 값을 가져온다 (1개 이상)
        // .stream() -> for문 처럼 값을 하나씩 읽게 해준다.
        // .map(MemoResponseDto::new) -> 하나씩 나오는 memo값을 MemoResponseDto의 생성자가 호출되어 변환해준다.
        // .toList -> list타입으로 변환해준다.

        /*
        // 위와 동일한 동작하는 코드
        List<MemoResponseDto> responseList = new ArrayList<>();
        for(Memo memo : memoList.values()){
            responseList.add(new MemoResponseDto(memo));
        }
        */
        return responseList;
    }
}
