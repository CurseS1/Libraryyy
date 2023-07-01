package com.example.library2.restcontroller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library2.common.Constants;
import com.example.library2.Entity.book;
import com.example.library2.Entity.Issue;
import com.example.library2.Entity.Issuedbook;
import com.example.library2.Entity.Member;
import com.example.library2.service.bookService;
import com.example.library2.service.IssueService;
import com.example.library2.service.IssuedbookService;
import com.example.library2.service.MemberService;

@RestController
@RequestMapping(value = "/rest/issue")
public class IssueRestController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private bookService bookService;
	
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private IssuedbookService issuedbookService;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@RequestParam Map<String, String> payload) {
		
		String memberIdStr = (String)payload.get("member");
		String[] bookIdsStr = payload.get("books").toString().split(",");
		
		Long memberId = null;
		List<Long> bookIds = new ArrayList<Long>();
		try {
			memberId = Long.parseLong( memberIdStr );
			for(int k=0 ; k<bookIdsStr.length ; k++) {
				bookIds.add( Long.parseLong(bookIdsStr[k]) );
			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			return "invalid number format";
		}
		
		Member member = memberService.get( memberId );
		List<book> books = bookService.get(bookIds);
		
		Issue issue = new Issue();
		issue.setMember(member);
		issue = issueService.addNew(issue);
		
		for( int k=0 ; k<books.size() ; k++ ) {
			book book = books.get(k);
			book.setStatus( Constants.book_STATUS_ISSUED );
			book = bookService.save(book);
			
			Issuedbook ib = new Issuedbook();
			ib.setbook( book );
			ib.setIssue( issue );
			issuedbookService.addNew( ib );
			
		}
		
		return "success";
	}
	
	@RequestMapping(value = "/{id}/return/all", method = RequestMethod.GET)
	public String returnAll(@PathVariable(name = "id") Long id) {
		Issue issue = issueService.get(id);
		if( issue != null ) {
			List<Issuedbook> issuedbooks = issue.getIssuedbooks();
			for( int k=0 ; k<issuedbooks.size() ; k++ ) {
				Issuedbook ib = issuedbooks.get(k);
				ib.setReturned( Constants.book_RETURNED );
				issuedbookService.save( ib );
				
				book book = ib.getbook();
				book.setStatus( Constants.book_STATUS_AVAILABLE );
				bookService.save(book);
			}
			
			issue.setReturned( Constants.book_RETURNED );
			issueService.save(issue);
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
	@RequestMapping(value="/{id}/return", method = RequestMethod.POST)
	public String returnSelected(@RequestParam Map<String, String> payload, @PathVariable(name = "id") Long id) {
		Issue issue = issueService.get(id);
		String[] issuedbookIds = payload.get("ids").split(",");
		if( issue != null ) {
			
			List<Issuedbook> issuedbooks = issue.getIssuedbooks();
			for( int k=0 ; k<issuedbooks.size() ; k++ ) {
				Issuedbook ib = issuedbooks.get(k);
				if( Arrays.asList(issuedbookIds).contains( ib.getId().toString() ) ) {
					ib.setReturned( Constants.book_RETURNED );
					issuedbookService.save( ib );
					
					book book = ib.getbook();
					book.setStatus( Constants.book_STATUS_AVAILABLE );
					bookService.save(book);
				}
			}
			
			return "successful";
		} else {
			return "unsuccessful";
		}
	}
	
}
