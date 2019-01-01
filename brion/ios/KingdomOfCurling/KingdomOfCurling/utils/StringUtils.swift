//
//  StringUtils.swift
//  KingdomOfCurling
//
//  Created by jucher park on 01/01/2019.
//  Copyright © 2019 jucher park. All rights reserved.
//

import Foundation

extension String {
    /**
     현재 날짜의 yyyy년 mm월 dd일 의 형식의 문자열을 출력함
     */
    static func getCurrentDate () -> String {
        let date = Date()
        let calendar = Calendar.current

        let year = calendar.component(.year, from: date)
        let month = calendar.component(.month, from: date)
        let day = calendar.component(.day, from: date)
        
        return  "\(year)년 \(month)월 \(day)일"
    }
    
    /**
     문자열이 이메일 포맷인지를 확인하는 함수
     */
    func isValidEmail() -> Bool {
        let regex = try! NSRegularExpression(pattern: "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", options: .caseInsensitive)
        
        return regex.firstMatch(in: self,
                                options: [],
                                range: NSRange(location: 0, length: count)) != nil
    }
}
