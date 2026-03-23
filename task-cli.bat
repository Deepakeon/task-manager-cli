#!/bin/bash

echo "🧪 Starting CLI Tests..."

run() {
  echo -e "\n🔹 Command: java -cp src TaskCLI $*"
  java -cp src TaskCLI "$@"
}

echo "------------------------------------------------"
echo "Test 1: No args (should show usage)"
run

echo "------------------------------------------------"
echo "Test 2: Add with no description"
run add

echo "------------------------------------------------"
echo "Test 3: Add a task"
run add "Learn Streams"

echo "------------------------------------------------"
echo "Test 4: List all tasks"
run list

echo "------------------------------------------------"
echo "Test 5: List only To-do"
run list todo

echo "------------------------------------------------"
echo "Test 6: Update task description (ID = 1)"
run update "Master Java Streams" 1

echo "------------------------------------------------"
echo "Test 7: Mark task in-progress (ID = 1)"
run mark-in-progress 1

echo "------------------------------------------------"
echo "Test 8: List in-progress tasks"
run list in_progress

echo "------------------------------------------------"
echo "Test 9: Mark task as done (ID = 1)"
run mark-done 1

echo "------------------------------------------------"
echo "Test 10: List done tasks"
run list done

echo "------------------------------------------------"
echo "Test 11: Delete task (ID = 1)"
run delete 1

echo "------------------------------------------------"
echo "Test 12: Delete nonexistent task (ID = 999)"
run delete 999

echo "------------------------------------------------"
echo "Test 13: Update nonexistent task"
run update "Should not work" 999

echo "------------------------------------------------"
echo "Test 14: Mark nonexistent task as done"
run mark-done 999

echo "------------------------------------------------"
echo "Test 15: List with invalid status"
run list trash

echo "✅ All tests finished."