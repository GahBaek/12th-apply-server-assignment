package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

// 메인 클래스.
public class Main {
    // 스터디룸 예약을 위한 클래스를 실행시키는 메인메소드.
    public static void main(String[] args) {
        BookingStuRoom.run();
    }

    // 스터디룸 예약 클래스.
    public class BookingStuRoom {
        // A, B, C 스터디룸.
        private static String[][] studyRoom = new String[14][4];

        // 사용자들의 문의사항.
        private static Complain[] complain = new Complain[100];

        // 문의 글 개수.
        private static int listNum = 0;

        public static void run() {
            initializeRooms();
            System.out.println("스터디룸 예약 프로그램입니다.");

            Scanner sc = new Scanner(System.in);
            // 예약 프로그램 반복문.
            while(true) {
                displayMenu();
                try {
                    int input = sc.nextInt();

                    switch (input) {
                        case 1:
                            reserveStudyRoom();
                            break;
                        case 2:
                            checkReservationStatus();
                            break;
                        case 3:
                            complaining();
                            break;
                        case 4:
                            displayComplains();
                            break;
                        case 5:
                            System.out.println("프로그램을 종료합니다...");
                            return;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                    sc.nextLine();
                }
            }
        }
        // 스터디룸 초기화.
        private static void initializeRooms() {
            studyRoom[0][0]="";
            studyRoom[0][1] = "A";
            studyRoom[0][2] = "B";
            studyRoom[0][3] = "C";

            studyRoom[1][0] = "오전 10시|"; studyRoom[2][0] = "오전 11시|"; studyRoom[3][0] = "오전 12시|";
            studyRoom[4][0] = "오후 01시|"; studyRoom[5][0] = "오후 02시|"; studyRoom[6][0] = "오후 03시|";
            studyRoom[7][0] = "오후 04시|"; studyRoom[8][0] = "오후 05시|"; studyRoom[9][0] = "오후 06시|";
            studyRoom[10][0] = "오후 07시|"; studyRoom[11][0] = "오후 08시|"; studyRoom[12][0] = "오후 09시|";
            studyRoom[13][0] = "오후 10시|";

            for(int i = 1; i <4; i++) {
                for(int k=1; k<14; k++) {
                    studyRoom[k][i]="X";
                }
            }
        }
        // 옵션.
        private static void displayMenu() {
            // 사용자의 종료 전까지 반복.
            System.out.println();
            System.out.println("----- 작업 리스트-----");
            System.out.println();
            System.out.println("1. 스터디룸 예약");
            System.out.println("2. 예약 현황 조회");
            System.out.println("3. 문의 남기기");
            System.out.println("4. 문의 리스트 보기");
            System.out.println("5. 프로그램 종료");
            System.out.println();
            System.out.print("작업을 선택하세요: ");
            System.out.println();
        }
        // 스터디룸 예약 메소드.
        private static void reserveStudyRoom() {
            String room="";
            int start;
            int end;
            Scanner scanner = new Scanner(System.in);
            boolean reservationFailed = false;

            System.out.print("예약할 스터디룸: ");
            room = scanner.next();
            System.out.print("사용 시작 시간: ");
            start = scanner.nextInt();
            start -= 9;	// 24시 -> 배열 순서에 맞도록.
            System.out.print("사용 종료 시간: ");
            end = scanner.nextInt();
            end -= 9;

            if(room.equals("A")||room.equals("B")||room.equals("C")) {
                int R=0;
                for (int i = 1; i <4; i++) {
                    if(studyRoom[0][i].equals(room))
                        R = i;
                }
                for(int i = start; i <end; i++) {
                    if (studyRoom[i][R].equals("O") ){
                        reservationFailed = true;
                        break;
                    }
                }

                if (!reservationFailed) {
                    for (int i = start; i <= end; i++) {
                        studyRoom[i][R] = "O";
                    }
                    System.out.println("\n예약이 완료되었습니다!\n");
                } else {
                    System.out.println("\n스터디룸 " + room + "는 해당 시간에 이미 예약되어 있습니다.\n예약에 실패했습니다");
                }
            }else {
                System.out.println("스터디룸 "+room+"은 존재하지 않습니다.\n"+"예약에 실패했습니다.");
            }
        }
        // 스터디룸 예약 현황 메소드.
        private static void checkReservationStatus() {
            System.out.println("----- 예약 현황 -----");
            for (int i = 0; i < studyRoom.length; i++) {
                for (int k = 0; k < studyRoom[i].length; k++) {
                    System.out.print(studyRoom[i][k] + "\t");
                }
                System.out.println();
            }
        }
        // 문의하기 메소드.
        private static void complaining() {
            String id="";
            String context = "";
            Scanner sc = new Scanner(System.in);
            System.out.println("----- 문의 남기기 -----");
            System.out.print("문의 아이디: ");
            id = sc.next();
            sc.nextLine();
            System.out.print("문의 내용: ");
            context = sc.nextLine();

            complain[listNum++] = new Complain(id, context);
        }
        // 문의 글 보기 메소드.
        private static void displayComplains() {
            System.out.println("----- 문의 리스트 보기 -----");
            for(int i = 0; i< listNum; i++) {
                complain[i].getString();
                System.out.println();
            }
        }
    }

}
