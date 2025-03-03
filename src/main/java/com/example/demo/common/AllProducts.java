package com.example.demo.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.vo.Products;

@Component
public class AllProducts {
  List<Products>  products= new ArrayList<>();

	public  List<Products> GetAllProducts() {
//				
		
		Products p1= new Products(1, 
								"https://rukminim2.flixcart.com/image/416/416/smart-band-tag/g/t/s/moov-now-moov-original-imaebq3ybgmpzvwr.jpeg", 
								"Black Strap,Ditch every excuse and start exercising everyday.",
								1999, 29.27
								);
		Products p2= new Products(2, 
				"https://rukminim2.flixcart.com/image/128/128/xif0q/massager/k/x/s/rechargeable-personal-body-massager-for-women-men-274-original-imaghbgerkncyhar.jpeg", 
				"LLM171 Powerful Electric Handheld Full Body Massager|",
				3200, 39.20
				);
		Products p3= new Products(3, 
				"https://rukminim2.flixcart.com/image/832/832/xif0q/shirt/m/q/r/xl-formal-shirt-metronaut-original-imah3g7mqnjcfytf.jpeg?q=70&crop=false", 
				"METRONAUT Men Regular Fit Solid Spread Collar Casual Shirt",
				1999, 21.4
				);
		Products p4= new Products(4, 
				"https://rukminim2.flixcart.com/image/832/832/xif0q/kids-dress/t/h/p/18-24-months-d20-ds-dresses-original-imahfhns4qezubra.jpeg?q=70&crop=false", 
				"DS DRESSES Girls Calf Length Party Dress  (Pink, 3/4 Sleeve)",
				699, 15.8
				);
		Products p5= new Products(5, 
				"https://rukminim2.flixcart.com/image/416/416/xif0q/washing-machine-new/e/a/p/-original-imah45kwm3qzhmfd.jpeg?q=70&crop=false", 
				"Whirlpool 7 kg Magic Clean 5 Star Fully Automatic Top Load Washing Machine Grey",
				19350, 26.9
				);
		Products p6= new Products(6, 
				"https://rukminim2.flixcart.com/image/416/416/xif0q/air-conditioner-new/a/f/t/-original-imah4jttabg9ngjn.jpeg?q=70&crop=false", 
				"Godrej 5-in-1 Convertible Cooling 2023 Model 1 Ton 3 Star Split Inverter i-sense Technology with Blue",
				42900, 38.9
				);
		
		
		
				products.addAll(Arrays.asList(p1,p2,p3,p4,p5,p6));
				
				return products;
	}
	
	

}	
