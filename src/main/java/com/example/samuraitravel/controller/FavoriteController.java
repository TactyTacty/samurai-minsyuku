package com.example.samuraitravel.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.Favorite;
import com.example.samuraitravel.entity.Houses;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.FavoriteRepository;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.FavoriteService;

@Controller
public class FavoriteController {
	private final FavoriteRepository favoriteRepository;
	private final HouseRepository houseRepository;
	private final FavoriteService favoriteService;

	public FavoriteController(FavoriteRepository favoriteRepository, HouseRepository houseRepository,
			FavoriteService favoriteService) {
		this.favoriteRepository = favoriteRepository;
		this.houseRepository = houseRepository;
		this.favoriteService = favoriteService;
	}

	@GetMapping("/favorites")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable, Model model) {
        // Userクラスのuserインスタンスを作成、userDetailsImpl.getUser()をセット
		User user = userDetailsImpl.getUser();
        // Page<Favorite>のfavoritePageインスタンスを作成、favoriteRepository.findByUserOrderByCreatedAtDescメソッド(引数はuser, pageable)をセット
        Page<Favorite> favoritePage = favoriteRepository.findByUserOrderByCreatedAtDesc(user, pageable);
        // modelのaddAttributeを使用して引数に"favoritePage", favoritePageをセット。
        model.addAttribute("favoritePage", favoritePage);
        // "favorites/index"を返却
        return "favorites/index";
    }  
    
    @PostMapping("/houses/{houseId}/favorites/create")
    public String create(@PathVariable(name = "houseId") Integer houseId,
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
			RedirectAttributes redirectAttributes,
			Model model) {
        // Houseクラスのhouseインスタンスを作成、houseRepository.getReferenceById(引数はhouseId)をセット
    	Houses house = houseRepository.getReferenceById(houseId);
        // Userクラスのuserインスタンスを作成、userDetailsImpl.getUser()をセット
        User user = userDetailsImpl.getUser();
        // favoriteServiceのcreateメソッドを使用(引数はrestaurant, user)
        favoriteService.create(house, user);
        // redirectAttributesのredirectAttributesメソッドを使用(引数は"successMessage", "お気に入りに追加しました。")
        redirectAttributes.addFlashAttribute("successMessage", "お気に入りに追加しました。");
        // "redirect:/houses/{houseId}"を返却
        return "redirect:/houses/{houseId}";
    }
    
    @PostMapping("/houses/{houseId}/favorites/{favoriteId}/delete")
    public String delete(@PathVariable(name = "favoriteId") Integer favoriteId, RedirectAttributes redirectAttributes) {
           // favoriteServiceのdeleteメソッドを使用(引数はfavoriteId)
    	favoriteRepository.deleteById(favoriteId);
           // redirectAttributesのredirectAttributesメソッドを使用(引数は"successMessage", "お気に入りを解除しました。")
           redirectAttributes.addFlashAttribute("successMessage", "お気に入りを解除しました。");
           // "redirect:/houses/{houseId}"を返却
           return "redirect:/houses/{houseId}";
    }
}
