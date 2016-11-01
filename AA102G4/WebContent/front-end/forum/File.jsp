if("userInsert".equals(action)){ // 來自addTravelScore.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			List<String> warningMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs",errorMsgs);
			req.setAttribute("warningMsgs",warningMsgs);
			TravelScoreService travelScoreSvc = new TravelScoreService();
			TravelScoreVO travelScoreVO = new TravelScoreVO();
			try{
				/1.接收請求參數 - 輸入格式的錯誤處理**/
				Integer tra_no = new Integer(req.getParameter("tra_no").trim());
				
				Integer mem_no = null;
				try{
					mem_no = new Integer(req.getParameter("mem_no").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("請先登入或是註冊!");
				}
				
				Integer tra_score = 1;
				Integer tra_score_status = 1;
				
				travelScoreVO = travelScoreSvc.getCheck(tra_no,mem_no);
				if(travelScoreVO != null) warningMsgs.add("您已經評分過囉!");
				
				travelScoreVO = new TravelScoreVO();
				travelScoreVO.setTra_no(tra_no);
				travelScoreVO.setMem_no(mem_no);
				travelScoreVO.setTra_score(tra_score);
				travelScoreVO.setTra_score_status(tra_score_status);
				// Send the use back to the form, if there were errors
				if(!errorMsgs.isEmpty() || !warningMsgs.isEmpty()){
					req.setAttribute("travelScoreVO",travelScoreVO); // 含有輸入格式錯誤的travelScoreVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/2.開始新增資料************/
				travelScoreVO = travelScoreSvc.addTravelScore(tra_no,mem_no,tra_score,tra_score_status);
				
				/****************3.新增完成,準備轉交(Send the Success view)/
				String url = "/front-end/travelPoint/listOneTravelPoint.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listOneTravelPoint.jsp
				successView.forward(req,res);				
				
				/其他可能的錯誤處理*******/
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/travelPoint/listOneTravelPoint.jsp");
				failureView.forward(req,res);
			}
		}