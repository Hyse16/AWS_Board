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
    public String list(Model model) {
        List<BoardDto> boardList = boardService.getBoardlist();

        model.addAttribute("boardList", boardList);
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

}
