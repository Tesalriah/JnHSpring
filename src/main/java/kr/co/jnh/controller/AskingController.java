package kr.co.jnh.controller;


import kr.co.jnh.domain.*;
import kr.co.jnh.service.AskingService;
import kr.co.jnh.service.QuestionService;
import kr.co.jnh.util.SessionIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage/asking")
public class AskingController {

    @Autowired
    AskingService askingService;
    @Autowired
    QuestionService questionService;

    // 문의목록 가져오기
    @GetMapping("/list")
    public String list(SearchCondition sc, HttpServletRequest request, Model m){

        String user_id =SessionIdUtil.getSessionId(request);

        Map<String,Object> map=new HashMap<>();
        map.put("id",user_id);
        map.put("sc",sc);

        try {
            // 해당 id의 글 갯수(문의글, 답변 포함)
            int idCount = askingService.getIdCount(user_id);

            // 해당 id 문의 목록 가져오기
            List<AskingDto> askingDtoList = askingService.idList(map);
            m.addAttribute("askingDtoList", askingDtoList);

            // 전체 게시물 갯수를 통해 PageHandler를 이용한 페이징 처리
            PageHandler ph = new PageHandler(idCount,sc);
            m.addAttribute("ph",ph);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/mypage/asking-list";
    }


    // 문의내역확인 - 상품문의
    @GetMapping("/question/list")
    public String qList(HttpServletRequest request,SearchCondition sc, Model m){

        String user_id = SessionIdUtil.getSessionId(request);

        Map<String,Object> map=new HashMap<>();
        map.put("user_id",user_id);
        map.put("sc",sc);

        try {
            // 해당 id의 글 갯수(문의글, 답변 포함)
            int idCount = questionService.getCount(map);

            // 해당 id 목록 가져오기
            List<Question> list = questionService.readInfo(map);
            m.addAttribute("qList", list);

            // 전체 게시물 갯수를 통해 PageHandler를 이용한 페이징 처리
            PageHandler ph = new PageHandler(idCount,sc);
            m.addAttribute("ph",ph);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/mypage/question-list";
    }


    // 하나의 게시물 읽기
    @GetMapping("/read")
    public String read(Model m, @RequestParam int no, SearchCondition sc, HttpServletRequest request){
        String user_id = SessionIdUtil.getSessionId(request);
        try {
            // no를 통해 게시물 불러오기
            List<AskingDto> askingDto = askingService.read(no);
            m.addAttribute("askingDto", askingDto);

            Map<String, Object> map = new HashMap<>();
            map.put("no",no);
            map.put("user_id", user_id);

            // 현재 no를 기준으로 앞뒤 2개의 게시물 bno 확인
            // Map형태로 arr0(이후후) arr1(이후) arr2(현재) arr3(이전) arr4(이전전) 게시물을의 bno 반환
            Map<String, Object> getBno=askingService.getPrevNext(map);
            // 게시물 리스트와 이전/다음 게시물 번호 초기화
            List<AskingDto> list = new ArrayList<>();
            ArrayList<Integer> arr = new ArrayList<>();
            Integer[] prevNext = new Integer[2];

            // arr0 ~ arr4의 실제 값이 있는지 확인해서 arr에 순서대로 bno값 추가 (없을 시 생략됨)
            for (int i = 0; i < 5; i++) {
                String key = "arr"+i;
                // getBno에 저장된 key라는 "키"에 연결된 "값"(arr+i)을 가져옴
                if (getBno.get(key) != null) {
                    arr.add(Integer.parseInt (getBno.get(key).toString()));
                }
            }
            // 저장된 arr의 bno값을 토대로 게시글을 list에 순서대로 저장
            for (int i = 0; i < arr.size(); i++) {
                List<AskingDto> aaa = askingService.read(arr.get(i));
                list.add(aaa.get(0));
            }
            // 이전게시글이 있을시 해당 bno를 prevNext[0]에 저장
            if (getBno.get("arr3") != null) {
                prevNext[0]=Integer.parseInt(getBno.get("arr3").toString());
            } if (getBno.get("arr1") != null) {
                prevNext[1]=Integer.parseInt(getBno.get("arr1").toString());
            }
            m.addAttribute("list", list);
            m.addAttribute("prevNext",prevNext);
            m.addAttribute("sc", sc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/mypage/asking";
    }


    // 게시물 삭제
    @PostMapping("/remove")
    public String remove( int no, HttpServletRequest request, Model m, SearchCondition sc){
        // 아이디 확인
        String user_id = SessionIdUtil.getSessionId(request);

        Map<String,Object> map = new HashMap<>();
        map.put("no",no);
        map.put("user_id", user_id);

        try {
            if (askingService.remove(map) > 0) {
                m.addAttribute("msg","삭제되었습니다.");
                m.addAttribute("url","list?page=" + sc.getPage());
            }else{
                throw new Exception("ASKING_REMOVE_FAIL");
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg","삭제에 실패했습니다.");
            m.addAttribute("url","list?page=" + sc.getPage());
        }
        return "alert";
    }

    @PostMapping("/question/remove")
    public String qRemove(HttpServletRequest request, Integer qno, Model m,SearchCondition sc){
        String user_id=SessionIdUtil.getSessionId(request);

        Map<String,Object> map = new HashMap<>();
        map.put("qno",qno);
        map.put("user_id",user_id);

        try {
            if (questionService.remove(map)>0) {
                m.addAttribute("msg","삭제되었습니다.");
                m.addAttribute("url","/jnh/mypage/asking/question/list");
            }else{
                throw new Exception("Question_REMOVE_FAIL");
            }

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg","삭제에 실패했습니다.");
            m.addAttribute("url","/jnh/mypage/asking/question/list");
        }

        return "alert";
    }


    // 게시물작성 페이지불러오기
    @GetMapping("/write")
    public String getWrite(){
        return "mypage/asking-write";
    }


    @PostMapping("/write")
    public String write(HttpServletRequest request, Model m, AskingDto askingDto, SearchCondition sc){
        String id = SessionIdUtil.getSessionId(request);

        try {
            // 필수값을 다 넣어주기 위해 작성
            int no = askingService.readNo();
            askingDto.setNo(no+1);  //마지막 숫자+1 해서 no지정
            askingDto.setCno(1);    // 문의글 작성 시 기본값 1(대기중)
            askingDto.setUser_id(id);
            askingDto.setState(0);  // 문의글 작성 시 기본값 0(대기중)

            // 제목, 내용이 비었을 경우 알림창
            if (askingRequired(askingDto)) {
                throw new Exception("NOT_BLANK");
            }
            // 작성에 실패할 경우
            if (askingService.write(askingDto)!=1) {
                throw new Exception("WRT_FAIL");
            } else {
                m.addAttribute("msg","작성되었습니다.");
                m.addAttribute("url","list?page=" + sc.getPage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 필수 값이 비어있을 시
            if(e.getMessage().equals("NOT_BLANK")){
                m.addAttribute("msg", "필수값을 입력해주세요");
            }else{ // 이외의 경우
                m.addAttribute("msg","실패했습니다.");
            }
            m.addAttribute("url", "write?no="+askingDto.getNo());
        }
        return "alert";
    }


    // 게시물 수정화면 요청
    @GetMapping("/modify")
    public String getMod(int no, Model m, SearchCondition sc, HttpServletRequest request){
        String id= SessionIdUtil.getSessionId(request);
        try {
            AskingDto askingDto = askingService.read(no).get(0);
            //작성자 id와 로그인id가 동일한 지 확인
            if (!id.equals(askingDto.getUser_id())) {
                m.addAttribute("msg","작성자만 수정 가능합니다.");
                m.addAttribute("url", "asking/list?page="+sc.getPage());
                return "alert";
            }
            m.addAttribute("askingDto", askingDto);

            // modify==1 라면 수정페이지로 처리하기 위해 전송
            m.addAttribute("modify","1");
            m.addAttribute("sc",sc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "mypage/asking-write";
    }


    // 게시물 수정하기
    @PostMapping("/modify")
    public String modify(AskingDto askingDto, HttpServletRequest request, Model m, SearchCondition sc){
        String id= SessionIdUtil.getSessionId(request);

        try {
            // 실패시 Exception 발생
            if (askingService.modify(askingDto)!=1) {
                throw new Exception("MOD_FAIL");
            } else {
                // 수정완료 시 목록으로
                m.addAttribute("msg","수정되었습니다.");
                m.addAttribute("url","list?page=" + sc.getPage());
                return "alert";
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 필수값이 비어있을시
            if(e.getMessage().equals("NOT_BLANK")){
                m.addAttribute("msg",e.getMessage());
            }else{ // 이외의 경우
                m.addAttribute("msg","MOD_FAIL");
            }
            return "mypage/asking-write";
        }
    }

    private boolean askingRequired(AskingDto askingDto) {
        return askingDto.getTitle() == null || askingDto.getTitle().isBlank()
                || askingDto.getContents() == null || askingDto.getContents().isBlank();
    }
}
