package AWS_Board.board.Controller;


import AWS_Board.board.Dto.BoardDto;
import AWS_Board.board.Service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor

public class BoardController {

    private BoardService boardService;


    @GetMapping("/")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list.html";
    }

    @GetMapping("/post")
    public String write() {
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    @GetMapping("/post/{po}")
    public String detail(@PathVariable("po") Long po, Model model) {
        BoardDto boardDTO = boardService.getPost(po);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail.html";
    }

    @GetMapping("/post/edit/{po}")
    public String edit(@PathVariable("po") Long po, Model model) {
        BoardDto boardDTO = boardService.getPost(po);

        model.addAttribute("boardDto", boardDTO);
        return "board/update.html";
    }

    @PutMapping("/post/edit/{po}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/";
    }

    @DeleteMapping("/post/{po}")
    public String delete(@PathVariable("po") Long po) {
        boardService.deletePost(po);

        return "redirect:/";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);
        return "board/list.html";
    }
}
